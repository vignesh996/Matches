package com.example.matches.helper

import com.example.matches.model.VideoDetails

class StaticVideosList {

    fun getInvoiceList(): ArrayList<VideoDetails> {

        var invoicesList = ArrayList<VideoDetails>()

//        invoicesList.add(VideoDetails("https://www.youtube.com/embed/sJPBuUDGqGg"))
        invoicesList.add(VideoDetails("l9UUhhczptc"))
        invoicesList.add(VideoDetails("qYvEuwrSiXc"))
        invoicesList.add(VideoDetails("J3qLwJEQ15c"))
        invoicesList.add(VideoDetails("WT1aWbVwixM"))
        invoicesList.add(VideoDetails("AnDBD1d0IKA"))
        invoicesList.add(VideoDetails("pitZeuSXBqQ"))
        invoicesList.add(VideoDetails("sJPBuUDGqGg"))


        return invoicesList
    }
}