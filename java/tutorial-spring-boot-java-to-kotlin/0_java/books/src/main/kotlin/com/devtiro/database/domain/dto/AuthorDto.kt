package com.devtiro.database.domain.dto

class AuthorDto {
    @JvmField
    var id: Long? = null
    @JvmField
    var name: String? = null
    @JvmField
    var age: Int? = null

    constructor(id: Long?, name: String?, age: Int?) {
        this.id = id
        this.name = name
        this.age = age
    }

    constructor()

    override fun equals(o: Any?): Boolean {
        if (o === this) return true
        if (o !is AuthorDto) return false
        val other = o
        if (!other.canEqual(this as Any)) return false
        val `this$id`: Any? = id
        val `other$id`: Any? = other.id
        if (if (`this$id` == null) `other$id` != null else `this$id` != `other$id`) return false
        val `this$name`: Any? = name
        val `other$name`: Any? = other.name
        if (if (`this$name` == null) `other$name` != null else `this$name` != `other$name`) return false
        val `this$age`: Any? = age
        val `other$age`: Any? = other.age
        return if (if (`this$age` == null) `other$age` != null else `this$age` != `other$age`) false else true
    }

    protected fun canEqual(other: Any?): Boolean {
        return other is AuthorDto
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = 1
        val `$id`: Any? = id
        result = result * PRIME + (`$id`?.hashCode() ?: 43)
        val `$name`: Any? = name
        result = result * PRIME + (`$name`?.hashCode() ?: 43)
        val `$age`: Any? = age
        result = result * PRIME + (`$age`?.hashCode() ?: 43)
        return result
    }

    override fun toString(): String {
        return "AuthorDto(id=" + id + ", name=" + name + ", age=" + age + ")"
    }

    class AuthorDtoBuilder internal constructor() {
        private var id: Long? = null
        private var name: String? = null
        private var age: Int? = null
        fun id(id: Long?): AuthorDtoBuilder {
            this.id = id
            return this
        }

        fun name(name: String?): AuthorDtoBuilder {
            this.name = name
            return this
        }

        fun age(age: Int?): AuthorDtoBuilder {
            this.age = age
            return this
        }

        fun build(): AuthorDto {
            return AuthorDto(id, name, age)
        }

        override fun toString(): String {
            return "AuthorDto.AuthorDtoBuilder(id=" + id + ", name=" + name + ", age=" + age + ")"
        }
    }

    companion object {
        @JvmStatic
        fun builder(): AuthorDtoBuilder {
            return AuthorDtoBuilder()
        }
    }
}
