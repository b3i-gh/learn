package com.devtiro.database.domain.entities

import jakarta.persistence.*

@Entity
@Table(name = "books")
class BookEntity {
    @JvmField
    @Id
    var isbn: String? = null
    @JvmField
    var title: String? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "author_id")
    var authorEntity: AuthorEntity? = null

    constructor(isbn: String?, title: String?, authorEntity: AuthorEntity?) {
        this.isbn = isbn
        this.title = title
        this.authorEntity = authorEntity
    }

    constructor()

    override fun equals(o: Any?): Boolean {
        if (o === this) return true
        if (o !is BookEntity) return false
        val other = o
        if (!other.canEqual(this as Any)) return false
        val `this$isbn`: Any? = isbn
        val `other$isbn`: Any? = other.isbn
        if (if (`this$isbn` == null) `other$isbn` != null else `this$isbn` != `other$isbn`) return false
        val `this$title`: Any? = title
        val `other$title`: Any? = other.title
        if (if (`this$title` == null) `other$title` != null else `this$title` != `other$title`) return false
        val `this$authorEntity`: Any? = authorEntity
        val `other$authorEntity`: Any? = other.authorEntity
        return if (if (`this$authorEntity` == null) `other$authorEntity` != null else `this$authorEntity` != `other$authorEntity`) false else true
    }

    protected fun canEqual(other: Any?): Boolean {
        return other is BookEntity
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = 1
        val `$isbn`: Any? = isbn
        result = result * PRIME + (`$isbn`?.hashCode() ?: 43)
        val `$title`: Any? = title
        result = result * PRIME + (`$title`?.hashCode() ?: 43)
        val `$authorEntity`: Any? = authorEntity
        result = result * PRIME + (`$authorEntity`?.hashCode() ?: 43)
        return result
    }

    override fun toString(): String {
        return "BookEntity(isbn=" + isbn + ", title=" + title + ", authorEntity=" + authorEntity + ")"
    }

    class BookEntityBuilder internal constructor() {
        private var isbn: String? = null
        private var title: String? = null
        private var authorEntity: AuthorEntity? = null
        fun isbn(isbn: String?): BookEntityBuilder {
            this.isbn = isbn
            return this
        }

        fun title(title: String?): BookEntityBuilder {
            this.title = title
            return this
        }

        fun authorEntity(authorEntity: AuthorEntity?): BookEntityBuilder {
            this.authorEntity = authorEntity
            return this
        }

        fun build(): BookEntity {
            return BookEntity(isbn, title, authorEntity)
        }

        override fun toString(): String {
            return "BookEntity.BookEntityBuilder(isbn=" + isbn + ", title=" + title + ", authorEntity=" + authorEntity + ")"
        }
    }

    companion object {
        @JvmStatic
        fun builder(): BookEntityBuilder {
            return BookEntityBuilder()
        }
    }
}
