package com.devtiro.database.domain.dto

class BookDto {
    @JvmField
    var isbn: String? = null
    @JvmField
    var title: String? = null
    var author: AuthorDto? = null

    constructor(isbn: String?, title: String?, author: AuthorDto?) {
        this.isbn = isbn
        this.title = title
        this.author = author
    }

    constructor()

    override fun equals(o: Any?): Boolean {
        if (o === this) return true
        if (o !is BookDto) return false
        val other = o
        if (!other.canEqual(this as Any)) return false
        val `this$isbn`: Any? = isbn
        val `other$isbn`: Any? = other.isbn
        if (if (`this$isbn` == null) `other$isbn` != null else `this$isbn` != `other$isbn`) return false
        val `this$title`: Any? = title
        val `other$title`: Any? = other.title
        if (if (`this$title` == null) `other$title` != null else `this$title` != `other$title`) return false
        val `this$author`: Any? = author
        val `other$author`: Any? = other.author
        return if (if (`this$author` == null) `other$author` != null else `this$author` != `other$author`) false else true
    }

    protected fun canEqual(other: Any?): Boolean {
        return other is BookDto
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = 1
        val `$isbn`: Any? = isbn
        result = result * PRIME + (`$isbn`?.hashCode() ?: 43)
        val `$title`: Any? = title
        result = result * PRIME + (`$title`?.hashCode() ?: 43)
        val `$author`: Any? = author
        result = result * PRIME + (`$author`?.hashCode() ?: 43)
        return result
    }

    override fun toString(): String {
        return "BookDto(isbn=" + isbn + ", title=" + title + ", author=" + author + ")"
    }

    class BookDtoBuilder internal constructor() {
        private var isbn: String? = null
        private var title: String? = null
        private var author: AuthorDto? = null
        fun isbn(isbn: String?): BookDtoBuilder {
            this.isbn = isbn
            return this
        }

        fun title(title: String?): BookDtoBuilder {
            this.title = title
            return this
        }

        fun author(author: AuthorDto?): BookDtoBuilder {
            this.author = author
            return this
        }

        fun build(): BookDto {
            return BookDto(isbn, title, author)
        }

        override fun toString(): String {
            return "BookDto.BookDtoBuilder(isbn=" + isbn + ", title=" + title + ", author=" + author + ")"
        }
    }

    companion object {
        @JvmStatic
        fun builder(): BookDtoBuilder {
            return BookDtoBuilder()
        }
    }
}
