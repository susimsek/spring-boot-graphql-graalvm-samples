package io.github.susimsek.springbootgraphqlgraalvmsamples.config

import com.apollographql.federation.graphqljava.Federation
import graphql.scalars.ExtendedScalars
import graphql.schema.GraphQLScalarType
import graphql.schema.idl.SchemaDirectiveWiring
import graphql.validation.rules.OnValidationErrorStrategy
import graphql.validation.rules.ValidationRules
import graphql.validation.schemawiring.ValidationSchemaWiring
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer


@Configuration(proxyBeanMethods = false)
class GraphqlConfig {

    @Bean
    fun federationTransform(): GraphQlSourceBuilderCustomizer {
        return GraphQlSourceBuilderCustomizer { builder ->
            builder.schemaFactory { registry, wiring ->
                Federation.transform(registry, wiring)
                    .fetchEntities { null }
                    .resolveEntityType { null }
                    .build()
            }
        }
    }

    @Bean
    fun validationSchemaDirective(): SchemaDirectiveWiring {
        val validationRules = ValidationRules.newValidationRules()
            .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
            .build()
        return ValidationSchemaWiring(validationRules)
    }

    @Bean
    fun objectScalarType(): GraphQLScalarType {
        return ExtendedScalars.Object
    }

    @Bean
    fun graphQLBigDecimalScalarType(): GraphQLScalarType {
        return ExtendedScalars.GraphQLBigDecimal
    }

    @Bean
    fun localeScalarType(): GraphQLScalarType {
        return ExtendedScalars.Locale
    }

    @Bean
    fun urlScalarType(): GraphQLScalarType {
        return ExtendedScalars.Url
    }

    @Bean
    fun positiveIntScalarType(): GraphQLScalarType {
        return ExtendedScalars.PositiveInt
    }

    @Bean
    fun uuidScalarType(): GraphQLScalarType {
        return ExtendedScalars.UUID
    }

    @Bean
    fun graphqlConfigurer(graphQLScalarTypes: List<GraphQLScalarType>,
                          validationSchemaDirective :SchemaDirectiveWiring): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { builder ->
            graphQLScalarTypes.forEach{builder.scalar(it)}
            builder.directiveWiring(validationSchemaDirective)
        }
    }
}
