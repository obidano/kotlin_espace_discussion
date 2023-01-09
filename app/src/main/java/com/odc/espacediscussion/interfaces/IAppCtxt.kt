package com.odc.espacediscussion.interfaces

import com.odc.espacediscussion.models.UserModel

interface IAppCtxt {
    fun retourArriere()
    fun changerpage(route: String)
    val connectedUSer: UserModel?
}