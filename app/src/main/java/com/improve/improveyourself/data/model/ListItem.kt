package com.improve.improveyourself.data.model

/**
 * Created by konk3r on 3/24/18.
 */
data class ListItem<T>(val type: Type, val item: T? = null, val text: String = "") {
    enum class Type { HEADER, LINE_ITEM }

    override fun toString(): String {
        when (type) {
            Type.HEADER -> return type.toString() + text
            Type.LINE_ITEM -> return type.toString() + item.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        val otherItem = other as? ListItem<*> ?: return false

        return otherItem.type == this.type &&
                otherItem.item == this.item &&
                otherItem.text == this.text
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + (item?.hashCode() ?: 0)
        result = 31 * result + text.hashCode()
        return result
    }
}