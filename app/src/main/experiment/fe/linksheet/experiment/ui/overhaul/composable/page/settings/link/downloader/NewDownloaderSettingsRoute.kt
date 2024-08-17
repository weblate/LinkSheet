package fe.linksheet.experiment.ui.overhaul.composable.page.settings.link.downloader

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import fe.android.compose.text.AnnotatedStringResourceContent.Companion.annotatedStringResource
import fe.android.compose.text.StringResourceContent.Companion.textContent
import fe.android.preference.helper.compose.StatePreference
import fe.composekit.component.ContentType
import fe.composekit.component.list.item.ContentPosition
import fe.composekit.component.list.item.type.SliderListItem
import fe.composekit.component.list.item.type.SwitchListItem
import fe.linksheet.R
import fe.linksheet.experiment.ui.overhaul.composable.component.list.item.type.PreferenceSwitchListItem
import fe.linksheet.experiment.ui.overhaul.composable.component.page.SaneScaffoldSettingsPage
import fe.linksheet.module.viewmodel.DownloaderSettingsViewModel
import fe.linksheet.util.AndroidVersion
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NewDownloaderSettingsRoute(
    onBackPressed: () -> Unit,
    viewModel: DownloaderSettingsViewModel = koinViewModel(),
) {
    val writeExternalStoragePermissionState = downloaderPermissionState()

    SaneScaffoldSettingsPage(
        headline = stringResource(id = R.string.settings_links_downloader__title_downloader),
        onBackPressed = onBackPressed
    ) {
        item(key = R.string.enable_downloader, contentType = ContentType.SingleGroupItem) {
            SwitchListItem(
                checked = viewModel.enableDownloader(),
                onCheckedChange = {
                    requestDownloadPermission(
                        writeExternalStoragePermissionState,
                        viewModel.enableDownloader,
                        it
                    )
                },
                position = ContentPosition.Trailing,
                headlineContent = textContent(R.string.enable_downloader),
                supportingContent = annotatedStringResource(R.string.enable_downloader_explainer),
            )
        }

        divider(id = R.string.options)

        group(size = 2) {
            item(key = R.string.downloader_url_mime_type) { padding, shape ->
                PreferenceSwitchListItem(
                    enabled = viewModel.enableDownloader(),
                    shape = shape,
                    padding = padding,
                    preference = viewModel.downloaderCheckUrlMimeType,
                    headlineContent = textContent(R.string.downloader_url_mime_type),
                    supportingContent = textContent(R.string.downloader_url_mime_type_explainer),
                )
            }

            item(key = R.string.request_timeout) { padding, shape ->
                SliderListItem(
                    enabled = viewModel.enableDownloader(),
                    shape = shape,
                    padding = padding,
                    valueRange = 0f..30f,
                    value = viewModel.requestTimeout().toFloat(),
                    onValueChange = { viewModel.requestTimeout(it.toInt()) },
                    valueFormatter = { it.toInt().toString() },
                    headlineContent = textContent(R.string.request_timeout),
                    supportingContent = annotatedStringResource(R.string.request_timeout_explainer),
                )
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun downloaderPermissionState() = rememberPermissionState(
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

@OptIn(ExperimentalPermissionsApi::class)
fun requestDownloadPermission(
    permissionState: PermissionState,
    state: StatePreference<Boolean>,
    newState: Boolean,
) {
    if (!AndroidVersion.AT_LEAST_API_29_Q && !permissionState.status.isGranted) {
        permissionState.launchPermissionRequest()
    } else state(newState)
}
