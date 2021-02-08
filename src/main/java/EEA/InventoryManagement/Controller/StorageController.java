package EEA.InventoryManagement.Controller;

import EEA.InventoryManagement.DTO.StorageReg;
import EEA.InventoryManagement.Entity.Storage;
import EEA.InventoryManagement.Entity.Supplier;
import EEA.InventoryManagement.Service.StorageService;
import EEA.InventoryManagement.Service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/storage")
@AllArgsConstructor
public class StorageController {

    @Autowired
    StorageService storageService;

    @Autowired
    SupplierService supplierService;

    @GetMapping("/loadStorage")
    public String loadStorage(Model model) {
        model.addAttribute("storage", new StorageReg());
        return "AddStorage";
    }

    @PostMapping("/createStorage")
    public String createStorage(@ModelAttribute("storage") StorageReg storageReg) {

        storageService.createStorage(storageReg);

        return "redirect:/admin/loadHome?success";

    }

    @GetMapping("/viewAllStorages")
    public String viewAllStorages(Model model)
    {
        model.addAttribute("storage", storageService.getAllStorages());
        return "ManageStorage";
    }

    @GetMapping("/loadStorageAddSupplier/{id}")
    public String loadAddSupplierPage(@PathVariable(value = "id") int id, Model model) {

        StorageReg storageReg = new StorageReg();
        storageReg.setStorageID(id);

        model.addAttribute("storageReg", storageReg);
        return "EnterSupplierID";
    }

    @PostMapping("/getStorageSupplier")
    public String loadSearchedSupplierPage(@ModelAttribute("storageReg") StorageReg storageReg, Model model) {

        Supplier supplier = supplierService.getSupplierByID(storageReg.getSupplierID());
        if( supplier != null)
        {
            storageReg.setSupplierID(supplier.getSupplierID());
            storageReg.setSupplier(supplier);
            model.addAttribute("storageReg", storageReg);
            return "AddSupplierToStorage";
        }

        return null;
    }

    @PostMapping("/addSupplierToStorage")
    public String saveStudentToBatch(@ModelAttribute("storageReg") StorageReg storageReg, Model model) {

        boolean save = storageService.addSupplierToStorage(storageReg);
        if(save ==true)
        {

            return "redirect:/admin/loadHome?success";
        }

        return "redirect:/admin/loadHome?failure";
    }







}
