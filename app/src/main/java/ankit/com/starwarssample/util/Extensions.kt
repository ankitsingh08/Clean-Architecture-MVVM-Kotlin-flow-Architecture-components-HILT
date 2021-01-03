package ankit.com.starwarssample.util

import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by AnkitSingh on 1/3/21.
 */
fun convertToInches(cm: String): String =
    BigDecimal(0.3937 * cm.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
