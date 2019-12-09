package com.ismin.opendataapp

import com.google.gson.annotations.SerializedName

data class Fields (
    @SerializedName("uo_lib")
    val name: String,

    @SerializedName("com_nom")
    val ville: String,

    @SerializedName("type_d_etablissement")
    val typeEtablissement: String,

    @SerializedName("url")
    val SiteInternet : String,

    @SerializedName("reg_nom")
    val region : String,

    @SerializedName("adresse_uai")
    val adresse: String,

    val coordonnees: ArrayList<Double>
)