package edu.pzks.security25.item;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AnnotatedRoutesController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/metrics")
    public String adminMetrics() { return "ADMIN metrics"; }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/manager/report")
    public String managerReport() { return "MANAGER report"; }

    @PreAuthorize("hasAnyRole('USER','MANAGER','ADMIN')")
    @GetMapping("/user/info")
    public String userInfo() { return "USER info"; }

    @PreAuthorize("hasAuthority('ITEM_READ')")
    @GetMapping("/items/list")
    public String listItems() { return "[{\"id\":1,\"name\":\"item1\"},{\"id\":2,\"name\":\"item2\"}]"; }

    @PreAuthorize("hasAuthority('ITEM_WRITE')")
    @PostMapping("/items/create")
    public String createItem() { return "Item created"; }

    @PreAuthorize("hasAuthority('ITEM_WRITE')")
    @PutMapping("/items/update")
    public String updateItem() { return "Item updated"; }

    @PreAuthorize("hasAuthority('AUDIT_READ')")
    @GetMapping("/audit/logs")
    public String auditLogs() { return "AUDIT logs"; }

    @PreAuthorize("hasAuthority('FINANCE_READ')")
    @GetMapping("/finance/balance")
    public String financeBalance() { return "{\"balance\":1000}"; }

    @PreAuthorize("hasAnyRole('ADMIN','AUDITOR')")
    @GetMapping("/audit/summary")
    public String auditSummary() { return "AUDIT summary"; }

    @PreAuthorize("hasRole('USER') and hasAuthority('ITEM_READ')")
    @GetMapping("/user/dashboard")
    public String dashboard() { return "USER dashboard"; }
}
