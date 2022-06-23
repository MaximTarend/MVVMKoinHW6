package by.hometrainng.mvvmkoin6.data.map

import android.Manifest
import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationService(context: Context) {

    private val client = LocationServices.getFusedLocationProviderClient(context)

    // обновляемая локация
    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun getLocationFlow() = callbackFlow {

        val locationRequest = LocationRequest.create().apply {
            interval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                trySend(result.lastLocation)
            }
        }
        client.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper()) // подписались

        awaitClose {
            client.removeLocationUpdates(locationCallback)
        }
    }.distinctUntilChanged { old, new ->
        old?.latitude != new?.latitude || old?.longitude != new?.longitude
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    suspend fun getCurrentLocation(): Location = suspendCoroutine { continuation ->

        client.lastLocation
            .addOnSuccessListener { location ->
                continuation.resume(location)
            }
/*            .addOnCanceledListener {
                continuation.resume(null)
            }
            .addOnFailureListener {
                continuation.resume()
            }*/
    }
}