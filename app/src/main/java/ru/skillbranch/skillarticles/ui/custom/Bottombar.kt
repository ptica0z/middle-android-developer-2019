package ru.skillbranch.skillarticles.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.shape.MaterialShapeDrawable
import ru.skillbranch.skillarticles.R
import ru.skillbranch.skillarticles.ui.custom.behaviors.BottombarBehavior
import ru.skillbranch.skillarticles.ui.custom.behaviors.SubmenuBehavior
import java.util.jar.Attributes
import java.util.zip.Inflater

class Bottombar@JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr), CoordinatorLayout.AttachedBehavior {
    override fun getBehavior(): CoordinatorLayout.Behavior<*> = BottombarBehavior()
    init {
        View.inflate(context, R.layout.layout_bottombar, this)
        val materialBg = MaterialShapeDrawable.createWithElevationOverlay(context)
        materialBg.elevation = elevation
        background = materialBg
    }

}