package com.kotion.mydemo

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kotion.mydemo.test.ClaA
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyTest {

    @Test
    fun Test_Main(){
        println("hellow kotion")
        testLambda()
        println("不定参数的结果："+myNumber(1,2,3,4,5,6))
        testStr()
        saceValue(1,"abc")
        saceValue("H","laj")
        saceValue(2.0f,"asd")
        testRange()
        testCal()

    }

    fun testLambda(){
        var myFun:(Int,Int)->Int={a,b ->a+b}
        println("result:"+myFun(1,2))
        println("result1:"+myFun1(1,2))
    }
    fun myFun1(a:Int,b:Int):Int{
        return a+b
    }
     fun myNumber(vararg num:Int):Int{
         var value:Int=0
         for (v in num){
             value+=v
         }
         return value
     }
    fun testStr(){
        var  num=10
        var str="this apple is $num"
        println("str:"+str)


      //  var name:String?=""
      //  name!!.toInt()
    }

    fun saceValue(key:Any,value:String){
        if (key is String){  //==instanceof
            println("this  ket type is String,value is $value")
        }else if(key is Int){
            println("this  ket type is Int,value is $value")
        }else if (key is Float){
            println("this  ket type is Float,value is $value")
        }
    }

    fun testCal(){
        var  claA= ClaA("a", 12)
        claA.save()
    }

    fun testRange(){
        for (i in 0..10) println("for i is $i")
    }

}