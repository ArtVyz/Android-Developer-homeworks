package com.example.homework17.entity

data class MarsPhotoInfo (
    val photos: List <Photos>
)

data class Photos (
    val id: Int,
    val sol: Int,
    val camera: Camera,
    val img_src: String,
    val earth_date: String,
    val rover: Rover
)

class Rover (
//    val id: Int,
    val name: String,
//    val landing_date:String,
//    val launch_date:String,
//    val status: String,
//    val max_sol: Int,
//    val max_date: String,
//    val total_photos: Int,
//    val cameras: List<Camera>
)
data class Camera (
//    val id: Int,
    val name: String,
//    val rover_id: Int,
//    val full_name: String
)
