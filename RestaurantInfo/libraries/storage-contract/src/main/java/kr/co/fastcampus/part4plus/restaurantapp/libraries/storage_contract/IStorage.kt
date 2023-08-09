package kr.co.fastcampus.part4plus.restaurantapp.libraries.storage_contract

interface IStorage {

    /**
     * @param key (key to identify the entry)
     * @param value (<T> needs to implement Serializable)
     * @see StorageKeys and package sharedenvelopes if the entry needs to be
     * accessible outside of the module scope
     */
    fun <T> save(key: String, value: T): Boolean
    fun exists(key: String): Boolean
    fun <T> get(key: String): T?
    fun remove(key: String): Boolean

    fun <T> save(key: String, value: T, callback: (Boolean) -> Unit)
    fun <T> get(key: String, callback: (data: T?) -> Unit)
    fun remove(key: String, callback: (Boolean) -> Unit)
    fun clear(): Boolean
}
