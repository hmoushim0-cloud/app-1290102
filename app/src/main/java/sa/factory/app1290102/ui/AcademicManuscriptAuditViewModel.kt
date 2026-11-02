package sa.factory.app1290102.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290102.data.AcademicManuscriptAuditRepository
import sa.factory.app1290102.model.AcademicManuscriptAudit
import sa.factory.app1290102.model.AuditWorkMilestone
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AcademicManuscriptAuditViewModel(
    private val repository: AcademicManuscriptAuditRepository
) : ViewModel() {
    val catalog: StateFlow<List<AuditWorkMilestone>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<AcademicManuscriptAudit?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: AcademicManuscriptAudit) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
