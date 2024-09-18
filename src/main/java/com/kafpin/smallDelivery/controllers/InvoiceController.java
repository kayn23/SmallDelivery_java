package com.kafpin.smallDelivery.controllers;

import com.kafpin.smallDelivery.dto.cargo.CargoDetailsDto;
import com.kafpin.smallDelivery.dto.cargo.CargoParamsDto;
import com.kafpin.smallDelivery.dto.invoice.InvoiceDetailsDto;
import com.kafpin.smallDelivery.dto.invoice.InvoiceParamsDto;
import com.kafpin.smallDelivery.dto.invoice.InvoiceSmallDetailsDto;
import com.kafpin.smallDelivery.models.Cargo;
import com.kafpin.smallDelivery.models.Invoice;
import com.kafpin.smallDelivery.models.Status;
import com.kafpin.smallDelivery.repositoryes.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
@Tag(name="Заказы")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class InvoiceController {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final CargoRepository cargoRepository;

    @GetMapping("")
    public List<InvoiceSmallDetailsDto> getAll(@RequestParam(name="page", required = false) String pageQuery,
                                                @RequestParam(name="per_page", required = false) String perPageQuery) {
        var p =  pageQuery != null ? Integer.parseInt(pageQuery) : 0;
        var page = p > 0 ? p - 1 : p;
        var per_page = perPageQuery == null ? pageQuery == null ? 10000: 100 : Integer.parseInt(perPageQuery);
        var pag = PageRequest.of(page, per_page);
        return repository.findAll(pag)
                .stream()
                .map(element -> mapper.map(element, InvoiceSmallDetailsDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public InvoiceDetailsDto show(@PathVariable String id) {
        var optionInvoice = repository.findById(Long.parseLong(id));
        checkEmpty(optionInvoice);
        return mapper.map(optionInvoice.get(), InvoiceDetailsDto.class);
    }

    @PostMapping("")
    public Long create(@RequestBody InvoiceParamsDto request) {
        var sender = userRepository.findById(request.getSenderId());
        checkEmpty(sender);
        var recipient = userRepository.findById(request.getRecipientId());
        checkEmpty(recipient);
        var endpoint = stockRepository.findById(request.getEndpoint());
        checkEmpty(endpoint);
        var invoice = Invoice.builder()
                .endpoint(endpoint.get())
                .sender(sender.get())
                .recipient(recipient.get())
                .status(Status.NEW)
                .price(request.getPrice())
                .build();
        repository.save(invoice);
        return invoice.getId();
    }
    @PatchMapping("/{id}")
    public InvoiceDetailsDto update(@PathVariable String id,
                                    @RequestBody InvoiceParamsDto request) {
        var optInvoice = repository.findById(Long.parseLong(id));
        checkEmpty(optInvoice);
        var sender = userRepository.findById(request.getSenderId());
        checkEmpty(sender);
        var recipient = userRepository.findById(request.getRecipientId());
        checkEmpty(recipient);
        var endpoint = stockRepository.findById(request.getEndpoint());
        checkEmpty(endpoint);
        var invoice = optInvoice.get();
        invoice.setEndpoint(endpoint.get());
        invoice.setRecipient(recipient.get());
        invoice.setSender(sender.get());
        invoice.setStatus(request.getStatus());
        invoice.setPrice(request.getPrice());
        repository.save(invoice);
        return mapper.map(invoice, InvoiceDetailsDto.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        var invoice = repository.findById(Long.parseLong(id));
        checkEmpty(invoice);
        repository.delete(invoice.get());
    }


    @GetMapping("/{invoice_id}/cargoes")
    @Operation(summary = "Список грвзов в заказе")
    public List<CargoDetailsDto> getCargo(@PathVariable String invoice_id) {
        var invoice = repository.findById(Long.parseLong(invoice_id));
        checkEmpty(invoice);
        return invoice.get()
                .getCargoes()
                .stream()
                .map(element -> mapper.map(element, CargoDetailsDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/{invoice_id}/cargoes")
    @Operation(summary = "Добавить груз к заказу")
    public Long addCargo(@PathVariable String invoice_id, @RequestBody CargoParamsDto request) {
        var invoice = repository.findById(Long.parseLong(invoice_id));
        checkEmpty(invoice);
        var cargo = Cargo.builder()
                .weight(request.getWeight())
                .build();
        invoice.get().getCargoes().add(cargo);
        cargoRepository.save(cargo);
        return cargo.getId();
    }

    @DeleteMapping("/{invoice_id}/cargoes/{id}")
    @Operation(summary = "Удалить груз из заказа")
    public void deleteCargo(@PathVariable String invoice_id, @PathVariable String id) {
        var cargo = cargoRepository.findById(Long.parseLong(id));
        checkEmpty(cargo);
        cargoRepository.delete(cargo.get());
    }

    private boolean checkEmpty(Optional entity) {
        if (entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return true;
    }

}
