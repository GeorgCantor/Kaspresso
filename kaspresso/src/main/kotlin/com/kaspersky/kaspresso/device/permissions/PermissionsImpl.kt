package com.kaspersky.kaspresso.device.permissions

import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import com.kaspersky.kaspresso.configurator.Configurator
import com.kaspersky.kaspresso.device.Device
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.extensions.other.getStackTraceAsString
import com.kaspersky.kaspresso.flakysafety.wait

/**
 * An implementation of Permissions interface.
 */
class PermissionsImpl : Permissions {

    private val packageInstallerPackageName = "com.android.packageinstaller"
    private val permissionDenyButtonId = "$packageInstallerPackageName:id/permission_deny_button"
    private val permissionAllowButtonId = "$packageInstallerPackageName:id/permission_allow_button"

    private val logger: UiTestLogger = Configurator.logger

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and allows permissions.
     */
    override fun allowViaDialog() = wait(timeoutMs = 1_000) { handlePermissionRequest(shouldAllowPermissions = true) }

    /**
     * Waits for 1 sec, passes the permission-requesting permissions dialog and denies permissions.
     */
    override fun denyViaDialog() = wait(timeoutMs = 1_000) { handlePermissionRequest(shouldAllowPermissions = false) }

    /**
     * Passes the permission-requesting permissions dialog.
     *
     * @param shouldAllowPermissions if set to true permissions will be allowed, otherwise will not.
     */
    private fun handlePermissionRequest(shouldAllowPermissions: Boolean) {
        try {
            val btnSelector = UiSelector()
                .clickable(true)
                .checkable(false)
                .resourceId(
                    if (shouldAllowPermissions)
                        permissionAllowButtonId
                    else
                        permissionDenyButtonId
                )

            val btn = Device.uiDevice.findObject(btnSelector)

            if (btn.exists()) {
                btn.click()
            }
        } catch (e: UiObjectNotFoundException) {
            logger.e("There are no permissions dialog to interact with.")
        } catch (e: Throwable) {
            logger.e(e.getStackTraceAsString())
            throw e
        }
    }
}