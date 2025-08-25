
# Commande Pompe (Android)
- Bouton **ON** (vert) : `1234#ON#`
- Bouton **OFF** (rouge) : `1234#OFF#`
- Demande le **numéro de téléphone** au premier lancement (modifiable via le bouton).

## Build local (Android Studio)
1. Ouvrir le projet dans Android Studio (JDK 17)
2. Laisser la synchronisation Gradle
3. Build > Generate Signed Bundle / APK… > APK > release
4. APK : `app/build/outputs/apk/release/app-release.apk`

## Build en ligne (GitHub Actions)
- Workflow inclus : `.github/workflows/android-release.yml`
- À chaque push sur `main`, l'APK est généré et disponible dans **Actions > Artifacts**
