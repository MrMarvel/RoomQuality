package ru.mrmarvel.camoletapp.data.sources

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import ru.gildor.coroutines.okhttp.await
import ru.mrmarvel.camoletapp.data.models.Flat
import ru.mrmarvel.camoletapp.data.models.Floor
import ru.mrmarvel.camoletapp.data.models.House
import ru.mrmarvel.camoletapp.data.models.Project
import ru.mrmarvel.camoletapp.data.models.Section

class ProjectSource {
    suspend fun getProjects(): List<Project> {
        try {
            val client = OkHttpClient()
            val queryParams =
                HttpUrl.parse("http://u1988986.isp.regruhosting.ru/rest")!!.newBuilder()
                    .addQueryParameter("sql", "SELECT * FROM projects;")
            val api = Request.Builder()
                .url(queryParams.build())
                .get()
                .build()
            // BLOCKING
            val response = client.newCall(api).await()
            val result = response.body()?.string() ?: "[]"
            Log.d("MYDEBUG", "Get Projects: $result")
            val jsonArray = JSONArray(result)
            val ormArray = mutableListOf<Project>()
            for (i in 0 until jsonArray.length()) {
                val x = GsonBuilder().create()
                    .fromJson(jsonArray.get(i).toString(), Project::class.java)
                ormArray.add(x)
            }
            return ormArray
        }
        catch (e: Exception){
            return mutableListOf<Project>()
        }

    }

    suspend fun getHouseByIdProject(ids: List<Int>): List<House> {
        try {
            val commaIds = ids.joinToString()
            val client = OkHttpClient()
            val queryParams =
                HttpUrl.parse("http://u1988986.isp.regruhosting.ru/rest")!!.newBuilder()
                    .addQueryParameter(
                        "sql",
                        "SELECT * FROM houses WHERE id_project in ($commaIds) ;"
                    )
            val api = Request.Builder()
                .url(queryParams.build())
                .get()
                .build()
            // BLOCKING
            val response = client.newCall(api).await()
            val result = response.body()?.string() ?: "[]"
            Log.d("MYDEBUG", "Get House: $result")
            val jsonArray = JSONArray(result)
            val ormArray = mutableListOf<House>()
            for (i in 0 until jsonArray.length()) {
                val x =
                    GsonBuilder().create().fromJson(jsonArray.get(i).toString(), House::class.java)
                ormArray.add(x)
            }
            return ormArray
        }
        catch (e: Exception){
            return mutableListOf<House>()
        }
    }

    suspend fun getSectionByIdHouse(ids: List<Int>): List<Section> {
        try {
            val commaIds = ids.joinToString()
            val client = OkHttpClient()
            val queryParams =
                HttpUrl.parse("http://u1988986.isp.regruhosting.ru/rest")!!.newBuilder()
                    .addQueryParameter(
                        "sql",
                        "SELECT * FROM sections WHERE id_house in ($commaIds) ;"
                    )
            val api = Request.Builder()
                .url(queryParams.build())
                .get()
                .build()
            // BLOCKING
            val response = client.newCall(api).await()
            val result = response.body()?.string() ?: "[]"
            Log.d("MYDEBUG", "Get Section: $result")
            val jsonArray = JSONArray(result)
            val ormArray = mutableListOf<Section>()
            for (i in 0 until jsonArray.length()) {
                val x = GsonBuilder().create()
                    .fromJson(jsonArray.get(i).toString(), Section::class.java)
                ormArray.add(x)
            }
            return ormArray
        }
        catch (e: Exception){
            return mutableListOf<Section>()
        }
    }

    suspend fun getFloorByIdSection(ids: List<Int>): List<Floor> {
        try {
            val commaIds = ids.joinToString()
            val client = OkHttpClient()
            val queryParams =
                HttpUrl.parse("http://u1988986.isp.regruhosting.ru/rest")!!.newBuilder()
                    .addQueryParameter(
                        "sql",
                        "SELECT * FROM floors WHERE id_section in ($commaIds) AND floor_number = 2;"
                    )
            val api = Request.Builder()
                .url(queryParams.build())
                .get()
                .build()
            // BLOCKING
            val response = client.newCall(api).await()
            val result = response.body()?.string() ?: "[]"
            Log.d("MYDEBUG", "Get Floor: $result")
            val jsonArray = JSONArray(result)
            val ormArray = mutableListOf<Floor>()
            for (i in 0 until jsonArray.length()) {
                val x =
                    GsonBuilder().create().fromJson(jsonArray.get(i).toString(), Floor::class.java)
                ormArray.add(x)
            }
            return ormArray
        }
        catch (e: Exception){
            return mutableListOf<Floor>()
        }
    }

    suspend fun getFlatsByIdFloor(ids: Int): List<Flat> {
        try {
            val client = OkHttpClient()
            val queryParams =
                HttpUrl.parse("http://u1988986.isp.regruhosting.ru/rest")!!.newBuilder()
                    .addQueryParameter("sql", "SELECT * FROM apartments where id_floor = $ids;")
            val api = Request.Builder()
                .url(queryParams.build())
                .get()
                .build()
            // BLOCKING
            val response = client.newCall(api).await()
            val result = response.body()?.string() ?: "[]"
            Log.d("MYDEBUG", "Get Flats: $result")
            val jsonArray = JSONArray(result)
            val ormArray = mutableListOf<Flat>()
            for (i in 0 until jsonArray.length()) {
                val x =
                    GsonBuilder().create().fromJson(jsonArray.get(i).toString(), Flat::class.java)
                ormArray.add(x)
            }
            return ormArray
        }
        catch (e: Exception){
            return mutableListOf<Flat>()
        }
    }

}