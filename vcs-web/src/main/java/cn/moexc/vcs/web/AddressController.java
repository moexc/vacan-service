package cn.moexc.vcs.web;

import cn.moexc.vcs.service.AddressService;
import cn.moexc.vcs.service.QueryAddressService;
import cn.moexc.vcs.service.dto.CreateAddressDTO;
import cn.moexc.vcs.web.config.auth.Auth;
import cn.moexc.vcs.web.config.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;
    private final QueryAddressService queryAddressService;

    @Autowired
    public AddressController(AddressService addressService, QueryAddressService queryAddressService) {
        this.addressService = addressService;
        this.queryAddressService = queryAddressService;
    }

    @GetMapping
    public R list(@Auth User user){
        return R.success(queryAddressService.list(user.getUserId()));
    }

    @PostMapping
    public R add(@RequestBody @Valid CreateAddressDTO createAddressDTO, @Auth User user){
        addressService.add(createAddressDTO, user.getUserId());
        return R.success();
    }

    @PutMapping("/{id}")
    public R update(@PathVariable("id") String id,
                    @RequestParam("operation") String operation,
                    @Auth User user){
        if (operation.equals("SetDefault")){
            addressService.setDefault(id);
        }
        return R.success();
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id, @Auth User user){
        addressService.delete(id);
        return R.success();
    }
}
