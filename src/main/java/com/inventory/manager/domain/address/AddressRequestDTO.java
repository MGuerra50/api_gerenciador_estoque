package com.inventory.manager.domain.address;

public record AddressRequestDTO(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String postalCode
) {
    public AddressRequestDTO(Address address) {
        this(
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getPostalCode()
        );
    }
}
