# Commande Pompe (Android, Kotlin)

- Première ouverture : demande du numéro, modifiable ensuite.
- Boutons ON (vert) / OFF (rouge) envoyant les SMS "1234#ON#" et "1234#OFF#".
- Gradle wrapper inclus + Workflow GitHub Actions (`.github/workflows/build.yml`).
- APK Release signé (debug) généré automatiquement dans **Actions > Artifacts**.

## GitHub
1) Uploade ce projet dans un repo (branche `main`).
2) Onglet **Actions** → **Build Android APK** → **Run workflow**.
3) Télécharge l’APK dans **Artifacts**.