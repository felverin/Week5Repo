package com.fel.quizz.data

import com.fel.quizz.model.Question

object QuestionRepository {

    val questions: List<Question> = listOf(
        Question(
            id = 1,
            text = "Ibu Kota Di Indonesia?",
            options = listOf("Surabaya", "Bandung", "Jakarta", "Medan"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 2,
            text = "Apakah Mata Kuliah VP susah?",
            options = listOf("Sangat Mudah", "Sulit", "Sangat Sulit", "Biasa Saja"),
            correctAnswerIndex = 0
        ),
        Question(
            id = 3,
            text = "Natal dirayakan pada bulan?",
            options = listOf("Agustus", "Oktober", "November", "Desember"),
            correctAnswerIndex = 3
        ),
        Question(
            id = 4,
            text = "Ada berapa hari dalam seminggu?",
            options = listOf("5", "7", "10", "11"),
            correctAnswerIndex = 1
        ),
        Question(
            id = 5,
            text = "Mata Kuliah VP pakai apa?",
            options = listOf("Android Studio", "Word", "Excel", "VS Code"),
            correctAnswerIndex = 0
        ),
        Question(
            id = 6,
            text = "Bahasa pemrograman utama untuk Android adalah?",
            options = listOf("Python", "Kotlin", "Swift", "C++"),
            correctAnswerIndex = 1
        ),
        Question(
            id = 7,
            text = "Komponen UI utama di Jetpack Compose disebut?",
            options = listOf("Fragment", "Activity", "Composable", "View"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 8,
            text = "Apa kepanjangan dari VP?",
            options = listOf(
                "Visual Programming",
                "Virtual Processing",
                "Variable Program",
                "Vector Presentation"
            ),
            correctAnswerIndex = 0
        ),
        Question(
            id = 9,
            text = "Planet terdekat dengan Matahari adalah?",
            options = listOf("Venus", "Merkurius", "Bumi", "Mars"),
            correctAnswerIndex = 1
        ),
        Question(
            id = 10,
            text = "Hewan yang dikenal sebagai Raja Hutan adalah?",
            options = listOf("Harimau", "Serigala", "Singa", "Gajah"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 11,
            text = "Hasil campuran warna merah dan kuning adalah?",
            options = listOf("Oranye", "Hijau", "Ungu", "Cokelat"),
            correctAnswerIndex = 0
        ),
        Question(
            id = 12,
            text = "Benua terbesar di dunia adalah?",
            options = listOf("Afrika", "Amerika", "Eropa", "Asia"),
            correctAnswerIndex = 3
        ),
        Question(
            id = 13,
            text = "Mata uang negara Jepang adalah?",
            options = listOf("Won", "Yen", "Dollar", "Rupiah"),
            correctAnswerIndex = 1
        ),
        Question(
            id = 14,
            text = "Gunung tertinggi di dunia adalah?",
            options = listOf("K2", "Kilimanjaro", "Mount Everest", "Fuji"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 15,
            text = "Rumus kimia dari air adalah?",
            options = listOf("H2O", "CO2", "O2", "NaCl"),
            correctAnswerIndex = 0
        ),
        Question(
            id = 16,
            text = "Warna bendera negara Indonesia adalah?",
            options = listOf("Merah Biru", "Merah Putih", "Putih Hijau", "Kuning Merah"),
            correctAnswerIndex = 1
        ),
        Question(
            id = 17,
            text = "Berapa hasil dari 12 x 12?",
            options = listOf("124", "134", "142", "144"),
            correctAnswerIndex = 3
        ),
        Question(
            id = 18,
            text = "Alat musik angklung berasal dari?",
            options = listOf("Jawa Tengah", "Jawa Timur", "Jawa Barat", "Bali"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 19,
            text = "Samudra terluas di dunia adalah?",
            options = listOf(
                "Samudra Pasifik",
                "Samudra Atlantik",
                "Samudra Hindia",
                "Samudra Arktik"
            ),
            correctAnswerIndex = 0
        ),
        Question(
            id = 20,
            text = "Penemu lampu pijar adalah?",
            options = listOf(
                "Nikola Tesla",
                "Thomas Alva Edison",
                "Alexander Bell",
                "Albert Einstein"
            ),
            correctAnswerIndex = 1
        ),
        Question(
            id = 21,
            text = "Berapa pasang kaki yang dimiliki laba-laba?",
            options = listOf("2 Pasang", "3 Pasang", "4 Pasang", "5 Pasang"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 22,
            text = "Olahraga menggunakan raket dan kok adalah?",
            options = listOf("Bulu Tangkis", "Tenis Meja", "Bola Voli", "Sepak Bola"),
            correctAnswerIndex = 0
        ),
        Question(
            id = 23,
            text = "Monas terletak di kota?",
            options = listOf("Bandung", "Jakarta", "Semarang", "Surabaya"),
            correctAnswerIndex = 1
        ),
        Question(
            id = 24,
            text = "Sistem operasi seluler buatan Google adalah?",
            options = listOf("iOS", "Windows", "Android", "Linux"),
            correctAnswerIndex = 2
        ),
        Question(
            id = 25,
            text = "Taj Mahal berada di negara?",
            options = listOf("Mesir", "Turki", "Arab Saudi", "India"),
            correctAnswerIndex = 3
        )
    )
}