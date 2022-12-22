package io.github.susimsek.springbootgraphqlgraalvmsamples.model

import java.math.BigDecimal
import java.util.Locale
import java.util.UUID

data class Message(
    val id: String,
    val content: String,
    val locale: Locale,
    val length: Int,
    val price: BigDecimal
)
