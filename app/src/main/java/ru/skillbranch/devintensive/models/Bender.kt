package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String) : Pair<String, Triple<Int, Int, Int>>{
        val validRes = isValid(answer)
        if (validRes == ""){
            if (question == Question.IDLE) return "Отлично - ты справился\nНа этом все, вопросов больше нет" to status.color
            return if (question.answers.contains(answer.toLowerCase())) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            } else {

                if (status == Status.CRITICAL) {
                    question = Question.NAME
                    status = Status.NORMAL
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                }
            }
        }
        else return validRes + "\n${question.question}" to status.color
    }

    fun isValid(answer: String): String{
        when(this.question){
            Question.NAME -> if (answer == "" || answer.first().isLowerCase()) return "Имя должно начинаться с заглавной буквы" else return ""
            Question.PROFESSION -> if (answer == "" || answer.first().isUpperCase()) return "Профессия должна начинаться со строчной буквы" else return ""
            Question.MATERIAL -> if (Regex("[0-9]").containsMatchIn(answer)) return "Материал не должен содержать цифр" else return ""
            Question.BDAY -> if (!Regex("^\\d+$").containsMatchIn(answer)) return "Год моего рождения должен содержать только цифры" else return ""
            Question.SERIAL -> if (!Regex("\\d{7}").containsMatchIn(answer)) return "Серийный номер содержит только цифры, и их 7" else return ""
            Question.IDLE -> return ""
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));

        fun nextStatus(): Status{
            return if(this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>){
        NAME("Как меня зовут?", listOf("бендер","bender")){
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик","bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл","дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
} 
