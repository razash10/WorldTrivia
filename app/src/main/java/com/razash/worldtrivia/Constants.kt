package com.razash.worldtrivia

object Constants {
    fun getQuestionsCountryOfFlag(): ArrayList<Question> {
        val questions = ArrayList<Question>()

        questions.add(Question("", "אנדורה", R.drawable.ad))
        questions.add(Question("", "איחוד האמירויות הערביות", R.drawable.ae))
        questions.add(Question("", "אפגניסטן", R.drawable.af))
        questions.add(Question("", "אנטיגואה וברבודה", R.drawable.ag))
        questions.add(Question("", "אלבניה", R.drawable.al))
        questions.add(Question("", "ארמניה", R.drawable.am))
        questions.add(Question("", "אנגולה", R.drawable.ao))
        questions.add(Question("", "אנטארקטיקה", R.drawable.aq))
        questions.add(Question("", "ארגנטינה", R.drawable.ar))

        return questions
    }

    fun getQuestionsCapitalOfFlag(): ArrayList<Question> {
        val questions = ArrayList<Question>()

        questions.add(Question("", "אנדורה לה ולה", R.drawable.ad))
        questions.add(Question("", "אבו דאבי", R.drawable.ae))
        questions.add(Question("", "קאבו", R.drawable.af))
        questions.add(Question("", "סנט ג'ונס", R.drawable.ag))
        questions.add(Question("", "טירנה", R.drawable.al))
        questions.add(Question("", "ירוואן", R.drawable.am))
        questions.add(Question("", "לואנדה", R.drawable.ao))
        questions.add(Question("", "בואנוס איירס", R.drawable.ar))

        return questions
    }

    fun getQuestionsCountryOfCapital(): ArrayList<Question> {
        val questions = ArrayList<Question>()

        questions.add(Question("אנדורה לה ולה", "אנדורה", 0))
        questions.add(Question("אבו דאבי", "איחוד האמירויות הערביות", 0))
        questions.add(Question("קאבו", "אפגניסטן", 0))
        questions.add(Question("סנט ג'ונס", "אנטיגואה וברבודה", 0))
        questions.add(Question("טירנה", "אלבניה", 0))
        questions.add(Question("ירוואן", "ארמניה", 0))
        questions.add(Question("לואנדה", "אנגולה", 0))
        questions.add(Question("בואנוס איירס", "ארגנטינה", 0))

        return questions
    }

    fun getQuestionsCapitalOfCountry(): ArrayList<Question> {
        val questions = ArrayList<Question>()

        questions.add(Question( "אנדורה", "אנדורה לה ולה", 0))
        questions.add(Question( "איחוד האמירויות הערביות", "אבו דאבי", 0))
        questions.add(Question( "אפגניסטן", "קאבול", 0))
        questions.add(Question( "אנטיגואה וברבודה", "סנט ג'ונס", 0))
        questions.add(Question( "אלבניה", "טירנה", 0))
        questions.add(Question( "ארמניה", "ירוואן", 0))
        questions.add(Question( "אנגולה", "לואנדה", 0))
        questions.add(Question( "ארגנטינה", "בואנוס איירס", 0))

        return questions
    }

}