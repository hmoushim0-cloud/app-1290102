package sa.factory.app1290102.data

import sa.factory.app1290102.model.AcademicManuscriptAudit
import sa.factory.app1290102.model.AuditWorkMilestone
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface AcademicManuscriptAuditRepository {
    fun getAvailableCatalog(): Flow<List<AuditWorkMilestone>>
    fun getActiveRequest(): Flow<AcademicManuscriptAudit?>
    suspend fun submitRequest(request: AcademicManuscriptAudit): Result<String>
}

class InMemoryAcademicManuscriptAuditRepository : AcademicManuscriptAuditRepository {
    private val _catalog = MutableStateFlow<List<AuditWorkMilestone>>(listOf(
        AuditWorkMilestone("1", "التدقيق اللغوي والنحوي الشامل", "مراجعة سلامة التراكيب اللغوية وضبط علامات الترقيم والإملاء الأكاديمي", 5000L, "verified"),
        AuditWorkMilestone("2", "ضبط التوثيق وتنسيق المراجع العلمية", "مطابقة المراجع الداخلية وقائمة المصادر وفق أنظمة APA وMLA بدقة تامة", 9500L, "assignment")
    ))
    private val _active = MutableStateFlow<AcademicManuscriptAudit?>(null)

    override fun getAvailableCatalog(): Flow<List<AuditWorkMilestone>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<AcademicManuscriptAudit?> = _active.asStateFlow()

    override suspend fun submitRequest(request: AcademicManuscriptAudit): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
