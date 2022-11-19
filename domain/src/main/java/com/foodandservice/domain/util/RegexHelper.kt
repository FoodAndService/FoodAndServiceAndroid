package com.foodandservice.domain.util

class RegexHelper {
    companion object {
        val phoneRegex =
            Regex("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*\$")
    }
}
