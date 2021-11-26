package ma.octo.assignement.service;

import ma.octo.assignement.domain.AuditVirement;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditVirementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;



public interface AutiService {
    public void auditVirement(String message);
    public void auditVersement(String message);
}
