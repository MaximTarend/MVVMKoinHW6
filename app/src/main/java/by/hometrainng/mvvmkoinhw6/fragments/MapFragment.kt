package by.hometrainng.mvvmkoinhw6.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.hometrainng.mvvmkoinhw6.databinding.FragmentMapBinding
import by.hometrainng.mvvmkoin6.data.map.LocationService
import by.hometrainng.mvvmkoin6.domain.model.Brewery
import by.hometrainng.mvvmkoinhw6.viewModels.BreweryMapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.LocationSource
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("MissingPermission")
class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val breweryMapViewModel by viewModel<BreweryMapViewModel>()

    private var googleMap: GoogleMap? = null

    private var locationListener : LocationSource.OnLocationChangedListener? = null

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isPermissionGranted ->
        if (isPermissionGranted) {

            breweryMapViewModel
                .locationFlow
                .onEach { locationService ->
                    locationService
                        .getCurrentLocation()
                        .let(::moveCameraToLocation)
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMapBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        breweryMapViewModel
            .locationFlow
            .map { service ->
                service
                    .getLocationFlow()
                    .onEach {
                        locationListener?.onLocationChanged(it)
                    }
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        with(binding) {

            button.setOnClickListener {
                breweryMapViewModel.onLoadMore()
            }

            mapView.getMapAsync { map ->
                googleMap = map.apply {
                    uiSettings.isCompassEnabled = true
                    uiSettings.isZoomControlsEnabled = true
                    uiSettings.isMyLocationButtonEnabled = true

                    isMyLocationEnabled = hasLocationPermission()

                    setLocationSource(object : LocationSource {
                        override fun activate(listener: LocationSource.OnLocationChangedListener?) {
                            locationListener = listener
                        }

                        override fun deactivate() {
                            locationListener = null
                        }
                    })
                }

                breweryMapViewModel
                    .loadBreweriesByDistFlow
                    .onEach {
                        println()
                        it.forEach { brewery ->
                            addBreweryMarkers(brewery)
                        }
                    }
                    .launchIn(viewLifecycleOwner.lifecycleScope)

                googleMap?.setOnMarkerClickListener { marker->
                    val breweryID = marker.tag.toString()

                    findNavController().navigate(
                        MapFragmentDirections.toBottomInfo(breweryID)
                    )
                    false
                }
            }
            mapView.onCreate(savedInstanceState)
        }
    }

    private fun addBreweryMarkers(brewery: Brewery) {
        val marker = googleMap?.addMarker(
            MarkerOptions()
                .title(brewery.name)
                .position(
                    LatLng(
                        brewery.latitude?.toDouble() ?: 0.0,
                        brewery.longitude?.toDouble() ?: 0.0
                    )
                )
                .alpha(0.6f)
        )
        marker?.tag = brewery.id
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
        googleMap = null
        _binding = null
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun moveCameraToLocation(location: Location) {
        val currentLocation = LatLng(location.latitude, location.longitude)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17f))
    }

}
