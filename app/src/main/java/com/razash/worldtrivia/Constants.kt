package com.razash.worldtrivia

object Constants {

    const val USER_NAME: String = "user_name"
    const val FINAL_SCORE: String = "final_score"

    fun getCountries() : ArrayList<Country> {
        val countries = ArrayList<Country>()

        countries.add(Country("אנדורה", "אנדורה לה ולה", R.drawable.ad))
        countries.add(Country("איחוד האמירויות הערביות", "אבו דאבי", R.drawable.ae))
        countries.add(Country("אפגניסטן", "קאבול", R.drawable.af))
        countries.add(Country("אנטיגואה וברבודה", "סנט ג'ונס", R.drawable.ag))
        countries.add(Country("אלבניה", "טירנה", R.drawable.al))
        countries.add(Country("ארמניה", "ירוואן", R.drawable.am))
        countries.add(Country("אנגולה", "לואנדה", R.drawable.ao))
        countries.add(Country("אנטארקטיקה", "", R.drawable.aq))
        countries.add(Country("ארגנטינה", "בואנוס איירס", R.drawable.ar))

        return countries
    }

}