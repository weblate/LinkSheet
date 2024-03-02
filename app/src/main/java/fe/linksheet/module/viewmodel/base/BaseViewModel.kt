package fe.linksheet.module.viewmodel.base

import androidx.lifecycle.ViewModel

import fe.linksheet.module.preference.AppPreferenceRepository
import fe.linksheet.module.preference.AppPreferences

abstract class BaseViewModel(
    preferenceRepository: AppPreferenceRepository,
//  protected val stateCache: StateCache = StateCache()
) : ViewModel() {
    var alwaysShowPackageName = preferenceRepository.getBooleanState(AppPreferences.alwaysShowPackageName)

    init {
//        Log.d("ViewModel", getTag())
    }

    // Unused for now
//    @Composable
//    fun bindStateLifetime(navController: NavController): () -> Unit {
//        val onBack: () -> Unit = {
//            stateCache.close()
//            navController.popBackStack()
//        }
//
//        BackHandler(true, onBack)
//
//        val lifecycleState = LocalLifecycleOwner.current.lifecycle.observeAsState()
//        LaunchedEffect(lifecycleState.state) {
//            if (lifecycleState.state == Lifecycle.Event.ON_PAUSE) stateCache.close()
//        }
//
//        return onBack
//    }
}
