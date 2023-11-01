package com.test.test1

import com.kms.free.R
import com.screen.common.KScreen
import io.guthub.kakaocup.kakao.image.KImageView
import io.guthub.kakaocup.kakao.text.KTextView
import io.guthub.kakaocup.kakao.text.KButton


object TestClass1 : KScreen<TestClass1>() {

    override val layoutId: Int? = TODO("Need To Implement")
    override val viewClass: Class<*>? = TODO("Need To Implement")

    val featureInfoImage = KImageView{ withId(R.id.feature_info_image) }
    val featureInfoTitle = KTextView{ withId(R.id.feature_info_title) }
    val featureInfoMessage = KTextView{ withId(R.id.feature_info_message) }
    val featureInfoPrimaryButton = KButton{ withId(R.id.feature_info_primary_button) }


    override fun BaseTestContext.waitForScreen() {
        TODO("Need To Implement")
    }

}
