package io.github.susimsek.springbootgraphqlgraalvmsamples.graphql.controller

import io.github.susimsek.springbootgraphqlgraalvmsamples.graphql.input.AddMessageInput
import io.github.susimsek.springbootgraphqlgraalvmsamples.model.Message
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.reactive.asPublisher
import kotlinx.coroutines.reactor.awaitSingle
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SubscriptionMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.util.*


@Controller
class HelloController {

    private val log = LoggerFactory.getLogger(javaClass)

    private val flow = MutableSharedFlow<Message>(replay = 1)

    @QueryMapping
    suspend fun hello(locale: Locale): Message {
        return Mono.just(Message(UUID.randomUUID().toString(),
            "Hello GraalVM", locale, 1, BigDecimal(10.2)))
            .awaitSingle()
    }

    @MutationMapping
    suspend fun createMessage(@Argument input: AddMessageInput, locale: Locale): Message {
        log.info("incoming locale: {}", locale)
        val message =  Message(
            UUID.randomUUID().toString(),
            input.content, locale, input.length, input.price)
        flow.emit(message)
        return Mono.just(
            message
        ).awaitSingle()
    }

    @SubscriptionMapping
    fun messageAdded(locale: Locale): Publisher<Message> {
        log.info("called messageAdded locale: {}", locale)
        return flow.asSharedFlow().asPublisher()
    }
}
