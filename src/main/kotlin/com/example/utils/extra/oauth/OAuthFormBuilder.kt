package com.example.utils.extra.oauth

import com.example.models.Category
import java.io.File

fun buildOAuthForm(): String {
    return buildString {
        appendLine("<!DOCTYPE html>")
        appendLine("<html>")
        appendLine("<head>")
        appendLine("    <title>Logowanie OAuth</title>")
        appendLine("</head>")
        appendLine("<body>")
        appendLine("    <h2>Logowanie przez OAuth</h2>")

        // Przycisk logowania przez Google
        appendLine("    <a href=\"/auth/google\">")
        appendLine("        <button>Zaloguj się przez Google</button>")
        appendLine("    </a>")

        // Przycisk logowania przez Facebook
        appendLine("    <a href=\"/auth/facebook\">")
        appendLine("        <button>Zaloguj się przez Facebook</button>")
        appendLine("    </a>")

        // Możesz dodać więcej przycisków dla innych dostawców OAuth
        appendLine("</body>")
        appendLine("</html>")
    }
}
