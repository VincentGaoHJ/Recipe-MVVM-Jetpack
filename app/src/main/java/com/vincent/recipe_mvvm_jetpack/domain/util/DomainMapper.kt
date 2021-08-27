package com.vincent.recipe_mvvm_jetpack.domain.util

/**
 * T: Stands for Network Entity, network.model.RecipeDto
 * DomainModel: domain.model.Recipe
 */
interface DomainMapper<T, DomainModel> {
    /// From an Network Entity to a Domain
    fun mapToDomainModel(model: T): DomainModel

    // From a DomainModel to a Network Entity
    fun mapFromDomainModel(domainModel: DomainModel): T
}