package com.upc.miappposta.util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.upc.miappposta.entidad.Cita
import com.upc.miappposta.entidad.Disponibilidad
import com.upc.miappposta.entidad.Especialidad
import com.upc.miappposta.entidad.Medico
import com.upc.miappposta.entidad.Paciente
import com.upc.miappposta.entidad.Usuario

class SQLiteHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSON){
    companion object{
        private const val DATABASE_NAME = "myposta.db"
        private const val DATABASE_VERSON = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        var query = "CREATE TABLE IF NOT EXISTS paciente(" +
                "id_paciente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo_documento TEXT," +
                "numero_documento TEXT," +
                "nombre TEXT," +
                "apellido TEXT," +
                "correo TEXT," +
                "telefono TEXT," +
                "direccion TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS usuario(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_paciente INTEGER," +
                "correo TEXT," +
                "password TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS medico(" +
                "id_medico INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_especialidad INTEGER," +
                "nombre TEXT," +
                "apellido TEXT," +
                "telefono TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS especialidad(" +
                "id_especialidad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "descripcion TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS disponibilidad(" +
                "id_disponibilidad INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_medico INTEGER," +
                "fecha TEXT," +
                "hora TEXT," +
                "estado TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS cita(" +
                "id_cita INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_paciente INTEGER," +
                "id_medico INTEGER," +
                "fecha TEXT," +
                "hora TEXT," +
                "estado TEXT," +
                "motivo_consulta TEXT" +
                ")"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS notificacion(" +
                "id_notificacion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_cita INTEGER," +
                "fecha TEXT," +
                "mensaje TEXT" +
                ")"
        db.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS paciente")
        db.execSQL("DROP TABLE IF EXISTS usuario")
        db.execSQL("DROP TABLE IF EXISTS medico")
        db.execSQL("DROP TABLE IF EXISTS especialidad")
        db.execSQL("DROP TABLE IF EXISTS disponibilidad")
        db.execSQL("DROP TABLE IF EXISTS cita")
        db.execSQL("DROP TABLE IF EXISTS notificacion")
        onCreate(db)

    }

    fun insertarMedico(nombre: String, apellido: String, telefono: String, idEspecialidad: Int): Long {
        val db = writableDatabase
        val valores = ContentValues()
        valores.put("nombre", nombre)
        valores.put("apellido", apellido)
        valores.put("telefono", telefono)
        valores.put("id_especialidad", idEspecialidad)
        return db.insert("medico", null, valores)
    }
    fun validarLogin(correo: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuario WHERE correo=? AND password=?",
            arrayOf(correo, password)
        )
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }

    fun llenarDatosIniciales(){
        val db = this.writableDatabase
        var paciente = Paciente(0,"DNI", "12345678", "Administrador", "Administrador", "admin@gmail.com", "987654321","Av. Principal 123")
        db.insert("paciente",null, llenarPaciente(paciente))

        paciente = Paciente(0,"DNI", "40041899", "Jose Antonio", "Cotos Conde", "josecotos@gmail.com", "996105835","Calle Secundaria 456")
        db.insert("paciente",null, llenarPaciente(paciente))

        paciente = Paciente(0,"DNI", "11111111", "Pedro", "Gómez", "james.wilson@example-pet-store.com", "555555555","Avenida Central 789")
        db.insert("paciente",null, llenarPaciente(paciente))

        paciente = Paciente(0,"DNI", "22222222", "Ana", "Martínez", "john.c.breckinridge@altostrat.com", "777777777","Calle Norte 321")
        db.insert("paciente",null, llenarPaciente(paciente))

        paciente = Paciente(0,"DNI", "33333333", "Luis", "Rodríguez", "james.moore.wayne@example-pet-store.com", "999999999","Avenida Sur 654")
        db.insert("paciente",null, llenarPaciente(paciente))

        var usuario = Usuario(0,1,"admin@gmail.com","admin")
        db.insert("usuario",null, llenarUsuario(usuario))

        usuario = Usuario(0,2,"josecotos@gmail.com","123456")
        db.insert("usuario",null, llenarUsuario(usuario))

        usuario = Usuario(0,3,"juand@gmail.com","123456")
        db.insert("usuario",null, llenarUsuario(usuario))

        usuario = Usuario(0,4,"william.henry.harrison@example-pet-store.com","password")
        db.insert("usuario",null, llenarUsuario(usuario))

        usuario = Usuario(0,5,"william.strong@my-own-personal-domain.com","letmein")
        db.insert("usuario",null, llenarUsuario(usuario))

        var especialidad = Especialidad(0,"Cardiología","Tratamiento de enfermedades del corazón")
        db.insert("especialidad",null, llenarEspecialidad(especialidad))

        especialidad = Especialidad(0,"Dermatología","Tratamiento de la piel y la cara")
        db.insert("especialidad",null, llenarEspecialidad(especialidad))

        especialidad = Especialidad(0,"Oftalmología","Tratamiento de las enfermedades del ojo")
        db.insert("especialidad",null, llenarEspecialidad(especialidad))

        especialidad = Especialidad(0,"Pediatría","Atención médica para niños y adolescentes")
        db.insert("especialidad",null, llenarEspecialidad(especialidad))

        var medico = Medico(0,1,"Juan","Pérez","987654321")
        db.insert("medico",null, llenarMedico(medico))

        medico = Medico(0,1,"María","López","123456789")
        db.insert("medico",null, llenarMedico(medico))

        medico = Medico(0,1,"Pedro","Gómez","555555555")
        db.insert("medico",null, llenarMedico(medico))

        medico = Medico(0,2,"Ana","Martínez","777777777")
        db.insert("medico",null, llenarMedico(medico))

        medico = Medico(0,2,"Luis","Rodríguez","999999999")
        db.insert("medico",null, llenarMedico(medico))

        medico = Medico(0,3,"Carlos","García","444444444")
        db.insert("medico",null, llenarMedico(medico))

        medico = Medico(0,3,"Laura","Fernández","666666666")
        db.insert("medico",null, llenarMedico(medico))

        medico = Medico(0,4,"Diego","Sánchez","222222222")
        db.insert("medico",null, llenarMedico(medico))

        var disponibilidad = Disponibilidad(0,1,"2025-10-20","10:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,1,"2025-10-20","11:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,1,"2025-10-20","14:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,1,"2025-10-20","15:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,2,"2025-10-20","09:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,2,"2025-10-20","10:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad =  Disponibilidad(0,2,"2025-10-20","11:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,3,"2025-10-20","14:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,3,"2025-10-20","15:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,3,"2025-10-20","16:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,4,"2025-10-20","11:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,4,"2025-10-20","12:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,5,"2025-10-20","10:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,5,"2025-10-20","11:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,5,"2025-10-20","12:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,6,"2025-10-20","11:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,6,"2025-10-20","12:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,7,"2025-10-20","09:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,7,"2025-10-20","10:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,8,"2025-10-20","09:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,8,"2025-10-20","10:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        disponibilidad = Disponibilidad(0,8,"2025-10-20","11:00","Pendiente")
        db.insert("disponibilidad",null, llenarDisponibilidad(disponibilidad))

        var cita = Cita(0,2,1,"2025-10-01","10:00","Atendido","Dolor de cabeza")
        db.insert("cita",null, llenarCita(cita))

        cita = Cita(0,2,1,"2025-10-14","11:00","Cancelado","Dolor de garganta")
        db.insert("cita",null, llenarCita(cita))

        cita = Cita(0,2,1,"2025-10-21","09:00","Pendiente","Dolor de Cabeza")
        db.insert("cita",null, llenarCita(cita))

        cita = Cita(0,3,2,"2025-10-02","10:00","Atendido","Dolor de Cabeza")
        db.insert("cita",null, llenarCita(cita))

        cita = Cita(0,3,2,"2025-10-15","11:00","Atendido","Dolor de Pie izquierdo")
        db.insert("cita",null, llenarCita(cita))


    }

    private fun llenarCita(cita: Cita): ContentValues {
        val valores = ContentValues()
        valores.put("id_paciente", cita.id_paciente)
        valores.put("id_medico", cita.id_medico)
        valores.put("fecha", cita.fecha)
        valores.put("hora", cita.hora)
        valores.put("estado", cita.estado)
        valores.put("motivo_consulta", cita.motivo_consulta)
        return valores
    }

    private fun llenarDisponibilidad(disponibilidad: Disponibilidad): ContentValues {
        val valores = ContentValues()
        valores.put("id_medico", disponibilidad.id_medico)
        valores.put("fecha", disponibilidad.fecha)
        valores.put("hora", disponibilidad.hora)
        valores.put("estado", disponibilidad.estado)
        return valores
    }

    private fun llenarMedico(medico: Medico): ContentValues{
        val valores = ContentValues()
        valores.put("id_especialidad", medico.id_especialidad)
        valores.put("nombre", medico.nombre)
        valores.put("apellido", medico.apellido)
        valores.put("telefono", medico.telefono)
        return valores
    }


    private fun llenarEspecialidad(especialidad: Especialidad): ContentValues{
        val valores = ContentValues()
        valores.put("nombre", especialidad.nombre)
        valores.put("descripcion", especialidad.descripcion)
        return valores
    }
    private fun llenarPaciente(paciente: Paciente): ContentValues{
        val valores = ContentValues()
        valores.put("tipo_documento", paciente.tipo_documento)
        valores.put("numero_documento", paciente.numero_documento)
        valores.put("nombre", paciente.nombre)
        valores.put("apellido", paciente.apellido)
        valores.put("correo", paciente.correo)
        valores.put("telefono", paciente.telefono)
        valores.put("direccion", paciente.direccion)
        return valores
    }

    private fun llenarUsuario(usuario: Usuario): ContentValues{
        val valores = ContentValues()
        valores.put("id_paciente", usuario.id_paciente)
        valores.put("correo", usuario.correo)
        valores.put("password", usuario.password)
        return valores
    }



}