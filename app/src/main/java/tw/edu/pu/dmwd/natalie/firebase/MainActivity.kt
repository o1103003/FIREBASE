package tw.edu.pu.dmwd.natalie.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    val db= Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var edtDrink: EditText =findViewById(R.id.edtDrink)
        var edtSize: EditText =findViewById(R.id.edtSize)
        var edtPrice: EditText =findViewById(R.id.edtPrice)
        var btnDelete:Button=findViewById(R.id.btnDelete)
       btnDelete.setOnClickListener({

            db.collection("drinks")

                .document(edtDrink.text.toString())

                .delete()

            Toast.makeText(this, "刪除資料", Toast.LENGTH_LONG).show()

        })

        var btnAdd: Button = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener({

            val drink = hashMapOf(

                "Drink" to edtDrink.text.toString(),
                "Size" to edtSize.text.toString(),
                "Price" to edtPrice.text.toString().toInt()

            )

            db.collection("drinks")

                .add(drink)

                .addOnSuccessListener { documentReference ->

                    Toast.makeText(this, "新增/異動資料成功",

                        Toast.LENGTH_LONG).show()

                }

                .addOnFailureListener { e ->

                    Toast.makeText(this, "新增/異動資料失敗：" + e.toString(),

                        Toast.LENGTH_LONG).show()

                }

        })

    }
}