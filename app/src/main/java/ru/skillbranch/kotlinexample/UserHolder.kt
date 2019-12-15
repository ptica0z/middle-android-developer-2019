package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting
import java.lang.IllegalArgumentException

object UserHolder {
    private val map = mutableMapOf<String, User>()

    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ): User{
        return User.makeUser(fullName, email, password)
            .also {
                if(map.get(it.login) != null) throw IllegalArgumentException("A user with this email already exists")
                map[it.login] = it
            }
    }

    fun registerUserByPhone(
        fullName: String,
        rawPhone: String
    ): User{
        val containLetter = "[A-Za-zА-Яа-я]".toRegex().containsMatchIn(rawPhone)
        val isFirstPlus = rawPhone.first() == '+'
        val phone = rawPhone.replace("[^+\\d]".toRegex(),"")
        val containNumbers = "[\\d]{11}".toRegex().containsMatchIn(phone)

        if(
            containLetter
            || !isFirstPlus
            || !containNumbers
        ) throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits")
        return User.makeUser(fullName, phon = rawPhone)
            .also {
                if(map.get(it.login) != null) throw IllegalArgumentException("A user with this phone already exists")
                map[it.login] = it
            }
    }

    fun requestAccessCode(login: String) : Unit{
        var cleanLogin = login
        if("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$".toRegex().matches(login))
            cleanLogin = login.replace("[^+\\d]".toRegex(), "")
        map[cleanLogin.trim()]?.changeAccessCode()
    }

    fun loginUser(login: String, password: String): String? {
        var cleanLogin = login
        if("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$".toRegex().matches(login))
            cleanLogin = login.replace("[^+\\d]".toRegex(), "")
        return map[cleanLogin.trim()]?.run{
            if(checkPassword(password)) this.userInfo
            else null
        }
    }

    fun importUsers(list: List<String>): List<User>{
        var userList = mutableListOf<User>()
        list.forEach{
            var failds = it.split(";")
            var saltPass = failds[2]?.split(":")
            var user = User.makeUser(failds[0]?.trim(), failds[1]?.trim(), saltPass[1]?.trim(), saltPass[0]?.trim(), failds[3]?.trim())
            user.changePassHash(saltPass[1]?.trim())
            userList.add(user)
        }
        return userList.toList()

    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder(){
        map.clear()
    }
}