package io.github.susimsek.springbootgraphqlgraalvmsamples.graphql.input

import java.math.BigDecimal


data class AddMessageInput(
    var content: String,
    var length: Int,
    var price: BigDecimal
)
