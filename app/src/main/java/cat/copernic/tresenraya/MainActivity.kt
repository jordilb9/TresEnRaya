package cat.copernic.tresenraya

import android.content.ContentValues.TAG
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    var clicB = arrayListOf<Int>()
    var tauler = IntArray(9) { 0 }
    var torn = 1
    var estat = 0
    var combG = IntArray(3){-1}
    lateinit var textGuanyador: TextView
    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textGuanyador = findViewById(R.id.estat_partida)
        textGuanyador.visibility = View.INVISIBLE

        clicB.addAll(listOf(R.id.button0, R.id.button1, R.id.button2,
            R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8))

    }

    fun posarFitxa(posicio: View) {

        if(estat == 0){
            var numB = clicB.indexOf(posicio.id)
            if(tauler[numB] == 0){
                torn = 1
                posicio.setBackgroundResource(R.drawable.ic_baseline_close_24)
                tauler[numB] = 1
                ++contador

                estat = comprovarGuanyador()
                finalPartida()

                var cont = 0
                if (estat == 0){
                    for (valor in tauler){
                        if(valor == 0) cont =+1
                    }
                    if(cont != 0){
                        var posicioM = comprovarTauler()
                        torn = -1
                        posarFitxaM(posicioM)
                        estat = comprovarGuanyador()
                        finalPartida()
                    }
                }
            }
        }
    }

    fun posarFitxaM(pos: Int){

        var posicioM = pos

        while(tauler[posicioM] !=0){
            posicioM = (0..8).random()
        }

        val b: Button = findViewById(clicB[posicioM])
        b.setBackgroundResource(R.drawable.ic_baseline_panorama_fish_eye_24)
        tauler[posicioM] = -1
        ++contador
    }

    fun finalPartida(){

        if (estat == 1 || estat == -1){
            if(torn == 1){
                textGuanyador.visibility = View.VISIBLE
                textGuanyador.setTextColor(Color.parseColor("#47f535"))

                for (valor in combG){
                    val b: Button = findViewById(clicB[valor])
                    b.setBackgroundResource(R.drawable.ic_baseline_close_24_green)
                }

            }else if(torn == -1){
                textGuanyador.visibility = View.VISIBLE
                textGuanyador.setText("Has perdut")
                textGuanyador.setTextColor(Color.parseColor("#e81010"))

                for (valor in combG){
                    val b: Button = findViewById(clicB[valor])
                    b.setBackgroundResource(R.drawable.ic_baseline_panorama_fish_eye_24_green)
                }
            }
        }else if(estat == 2){
            textGuanyador.visibility = View.VISIBLE
            textGuanyador.setText("Empat")
        }


    }

    fun comprovarGuanyador(): Int{

        var nouEstat = 0

        if(abs(tauler[0] + tauler[1] + tauler[2]) == 3 ){
            combG = intArrayOf(0,1,2)
            nouEstat = 1*torn
        }else if (abs(tauler[3] + tauler[4] + tauler[5]) == 3){
            combG = intArrayOf(3,4,5)
            nouEstat = 1*torn
        }else if(abs(tauler[6] + tauler[7] + tauler[8]) == 3){
            combG = intArrayOf(6,7,8)
            nouEstat = 1*torn
        }else if(abs(tauler[0] + tauler[3] + tauler[6]) == 3){
            combG = intArrayOf(0,3,6)
            nouEstat = 1*torn
        }else if(abs(tauler[1] + tauler[4] + tauler[7]) == 3) {
            combG = intArrayOf(1,4,7)
            nouEstat = 1 * torn
        }else if(abs(tauler[2] + tauler[5] + tauler[8]) == 3) {
            combG = intArrayOf(2,5,8)
            nouEstat = 1 * torn
        }else if(abs(tauler[0] + tauler[4] + tauler[8]) == 3){
            combG = intArrayOf(0,4,8)
            nouEstat = 1*torn
        }else if(abs(tauler[6] + tauler[4] + tauler[2]) == 3){
            combG = intArrayOf(6,4,2)
            nouEstat = 1*torn
        }else if (contador == 9){
            nouEstat = 2
        }

        return nouEstat
    }

    fun comprovarTauler(): Int{

        var posicioM = (0..8).random()
//        var opcions: String = ""
//        var opcionsM: String = ""
        var posC = arrayListOf<Int>()
        var posCM = arrayListOf<Int>()

        for (pos in tauler.indices){

            if(tauler[pos] == 1){
                if(!posC.contains(pos))
                posC.add(pos)
            }else{
                if(tauler[pos] == -1){
                    if (!posCM.contains(pos))
                    posCM.add(pos)
                }
            }
        }

        posC.sortedBy { it }

        //for (pos2 in posC){

           // opcions += pos2.toString()

            if(posC.contains(0) && posC.contains(1)){
                posicioM = 2
            }else{
                if (posC.contains(0) && posC.contains(2)){
                    posicioM = 1
                }else{
                    if (posC.contains(1) && posC.contains(2)){
                        posicioM = 0
                    }
                }
            }
            if(posC.contains(3) && posC.contains(4)){
                posicioM = 5
            }else{
                if (posC.contains(3) && posC.contains(5)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(5)){
                        posicioM = 3
                    }
                }
            }
            if(posC.contains(6) && posC.contains(7)){
                posicioM = 8
            }else{
                if (posC.contains(6) && posC.contains(8)){
                    posicioM = 7
                }else{
                    if (posC.contains(7) && posC.contains(8)){
                        posicioM = 6
                    }
                }
            }
            if(posC.contains(0) && posC.contains(3)){
                posicioM = 6
            }else{
                if (posC.contains(0)  && posC.contains(6)){
                    posicioM = 3
                }else{
                    if (posC.contains(3) && posC.contains(6)){
                        posicioM = 0
                    }
                }
            }
            if(posC.contains(1) && posC.contains(4)){
                posicioM = 7
            }else{
                if (posC.contains(1) && posC.contains(7)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(7)){
                        posicioM = 1
                    }
                }
            }
            if(posC.contains(2) && posC.contains(5)){
                posicioM = 8
            }else{
                if (posC.contains(2) && posC.contains(8)){
                    posicioM = 5
                }else{
                    if (posC.contains(5) && posC.contains(8)){
                        posicioM = 2
                    }
                }
            }
            if(posC.contains(0) && posC.contains(4)){
                posicioM = 8
            }else{
                if (posC.contains(0) && posC.contains(8)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(8)){
                        posicioM = 0
                    }
                }
            }
            if(posC.contains(2) && posC.contains(4)){
                posicioM = 6
            }else{
                if (posC.contains(2) && posC.contains(6)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(6)){
                        posicioM = 2
                    }
                }
            }

        //}

        posCM.sortedBy { it }

       // for (pos3 in posCM){

           //opcionsM += pos3.toString()

            if(posC.contains(0) && posC.contains(1)){
                posicioM = 2
            }else{
                if (posC.contains(0) && posC.contains(2)){
                    posicioM = 1
                }else{
                    if (posC.contains(1) && posC.contains(2)){
                        posicioM = 0
                    }
                }
            }
            if(posC.contains(3) && posC.contains(4)){
                posicioM = 5
            }else{
                if (posC.contains(3) && posC.contains(5)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(5)){
                        posicioM = 3
                    }
                }
            }
            if(posC.contains(6) && posC.contains(7)){
                posicioM = 8
            }else{
                if (posC.contains(6) && posC.contains(8)){
                    posicioM = 7
                }else{
                    if (posC.contains(7) && posC.contains(8)){
                        posicioM = 6
                    }
                }
            }
            if(posC.contains(0) && posC.contains(3)){
                posicioM = 6
            }else{
                if (posC.contains(0) && posC.contains(6)){
                    posicioM = 3
                }else{
                    if (posC.contains(3) && posC.contains(6)){
                        posicioM = 0
                    }
                }
            }
            if(posC.contains(1) && posC.contains(4)){
                posicioM = 7
            }else{
                if (posC.contains(1) && posC.contains(7)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(7)){
                        posicioM = 1
                    }
                }
            }
            if(posC.contains(2) && posC.contains(5)){
                posicioM = 8
            }else{
                if (posC.contains(2) && posC.contains(8)){
                    posicioM = 5
                }else{
                    if (posC.contains(5) && posC.contains(8)){
                        posicioM = 2
                    }
                }
            }
            if(posC.contains(0) && posC.contains(4)){
                posicioM = 8
            }else{
                if (posC.contains(0) && posC.contains(8)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(8)){
                        posicioM = 0
                    }
                }
            }
            if(posC.contains(2) && posC.contains(4)){
                posicioM = 6
            }else{
                if (posC.contains(2) && posC.contains(6)){
                    posicioM = 4
                }else{
                    if (posC.contains(4) && posC.contains(6)){
                        posicioM = 2
                    }
                }
            }
       // }

        return posicioM

    }
}