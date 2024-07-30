package com.lovevery.exam.utils.extensions

import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.showShimmerLayout(show: Boolean) {
    if (show) startShimmer() else stopShimmer()
    show(show)
}
