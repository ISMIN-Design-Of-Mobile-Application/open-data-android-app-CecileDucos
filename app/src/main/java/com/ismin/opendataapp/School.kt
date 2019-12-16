package com.ismin.opendataapp

import java.io.Serializable


data class School (val name : String?,
                   val ville : String?,
                   val typeEtablissement : String?,
                   val SiteInternet : String?,
                   val region : String?,
                   val adresse : String?,
                   val latitude: Double,
                   val  longitude : Double) : Serializable