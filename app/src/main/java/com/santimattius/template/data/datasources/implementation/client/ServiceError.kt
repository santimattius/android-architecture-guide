package com.santimattius.template.data.datasources.implementation.client

import com.santimattius.template.core.data.AppException

data class ServiceError(val error: String) : AppException(error)
