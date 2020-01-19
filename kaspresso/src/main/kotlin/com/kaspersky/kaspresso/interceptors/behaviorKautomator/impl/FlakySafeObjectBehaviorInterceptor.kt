package com.kaspersky.kaspresso.interceptors.behaviorKautomator.impl

import com.kaspersky.components.kautomator.intercepting.interaction.UiObjectInteraction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAction
import com.kaspersky.components.kautomator.intercepting.operation.UiObjectAssertion
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProvider
import com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl
import com.kaspersky.kaspresso.interceptors.behaviorKautomator.ObjectBehaviorInterceptor
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.params.FlakySafetyParams

/**
 * The implementation of [ObjectBehaviorInterceptor] and [FlakySafetyProvider] interfaces.
 * Provides system flaky safety functionality for [UiObjectInteraction.perform] and [UiObjectInteraction.check] calls.
 */
class FlakySafeObjectBehaviorInterceptor(
    params: FlakySafetyParams,
    logger: UiTestLogger
) : ObjectBehaviorInterceptor,
    FlakySafetyProvider by FlakySafetyProviderImpl(params, logger) {

    /**
     * Wraps the given [activity] invocation with the flaky safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param assertion the intercepted [UiObjectAssertion].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptCheck(
        interaction: UiObjectInteraction,
        assertion: UiObjectAssertion,
        activity: () -> T
    ): T = flakySafely(action = activity)

    /**
     * Wraps the given [activity] invocation with the flaky safety.
     *
     * @param interaction the intercepted [UiObjectInteraction].
     * @param action the intercepted [UiObjectAction].
     * @param activity the activity to invoke.
     */
    override fun <T> interceptPerform(
        interaction: UiObjectInteraction,
        action: UiObjectAction,
        activity: () -> T
    ): T = flakySafely(action = activity)
}