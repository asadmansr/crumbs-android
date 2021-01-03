package com.asadmansoor.crumbs.internal

import java.io.IOException

class InvalidInputException(warning: String) : IOException(warning)

class UnexpectedException : Exception()
