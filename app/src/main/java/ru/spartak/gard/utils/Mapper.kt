package ru.spartak.gard.utils

interface Mapper<Domain, T> {
    fun toDomain(t: T): Domain
    fun fromDomain(domain: Domain): T
}