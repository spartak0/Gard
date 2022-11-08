package ru.spartak.gard.utils

object ParserDecimal {
    fun pars(decimal:Int):String{
        val stringDecimal = decimal.toString().reversed()
        var new=""
        for (i in stringDecimal.indices){
            if (i%3==0) new+=" "
            new+= stringDecimal[i]
        }
        return new.reversed()
    }

    fun pars(decimal:String):String{
        val stringDecimal = decimal.reversed()
        var new=""
        for (i in stringDecimal.indices){
            if (i%3==0) new+=" "
            new+= stringDecimal[i]
        }
        return new.reversed()
    }
}