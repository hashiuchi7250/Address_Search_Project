package com.example.address.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.address.dto.AddressDto;
import com.example.address.form.AddressEditForm;
import com.example.address.form.AddressForm;
import com.example.address.form.AddressKeyWordForm;
import com.example.address.service.AddressCsvDataService;
import com.example.address.service.AddressEditService;
import com.example.address.service.AddressService;

@Controller
@RequestMapping("address")
public class AddressController {

	@Autowired
	AddressService addressService;

	@Autowired
	AddressCsvDataService csvDataService;

	@Autowired
	AddressEditService editService;

	@ModelAttribute
	AddressForm setupForm() {
		return new AddressForm();
	}

	// Demo画面の初期表示
	@RequestMapping("search/demo")
	public ModelAndView searchDemo(ModelAndView mav) {
		mav.setViewName("demo");
		return mav;
	}

	// 住所検索画面の初期表示
	@RequestMapping("search/top")
	public ModelAndView searchTop(ModelAndView mav) {
		mav.setViewName("search");
		return mav;
	}

	// 住所検索ボタン押下時の処理
	@GetMapping("search/addressReference")
	public ModelAndView addressReference(@Valid AddressForm form, BindingResult result,
			ModelAndView mav, @RequestParam("zipcode") String zipcode) {

		if (result.hasErrors()) {
			mav.addObject("msg", "（Error = 住所未入力）");
			mav.setViewName("search");
			return mav;
		}

		AddressDto addressDto = new AddressDto();
		addressDto.setZipcode(zipcode);

		List<AddressDto> findResultAddress = addressService.findAddress(addressDto);

		mav.addObject("displayZipcode", zipcode);
		mav.addObject("findResultAddress", findResultAddress);
		mav.setViewName("search");
		return mav;
	}

	// 地名検索画面の初期表示
	@RequestMapping("search/city")
	public ModelAndView keyword(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	// 地名検索ボタン押下時の処理
	@GetMapping("search/keyword")
	public ModelAndView keywordSearch(@Valid AddressKeyWordForm form, BindingResult result,
			@RequestParam("keyword") String keyword, ModelAndView mav) {

		if (result.hasErrors()) {
			mav.addObject("msg", "（Error = 県名また市町村名を入力）");
			mav.setViewName("index");
			return mav;
		}

		AddressDto addressDto = new AddressDto();
		addressDto.setKeyword(keyword);

		List<AddressDto> findResultAddress = addressService.findAddress(addressDto);

		mav.addObject("keyword", keyword);
		mav.addObject("findResultAddress", findResultAddress);
		mav.setViewName("index");
		return mav;
	}

	// CSV出力ボタン押下時の処理
	@RequestMapping("search/download_csv")
	public ResponseEntity<byte[]> download_csv(AddressForm form) throws IOException {

		AddressDto addressDto = new AddressDto();
		addressDto.setZipcode(form.getZipcode());

		StringBuilder sb = new StringBuilder();
		sb.append("郵便番号, 都道府県, 住所１, 住所２ \n");

		String resultCsvData = csvDataService.makeCsvData(addressDto, sb);

    	HttpHeaders httpHeaders = new HttpHeaders();
    	httpHeaders.add("Content-Type", "text/csv; charset=MS932");
    	httpHeaders.setContentDispositionFormData("filename", "File.csv");
	    return new ResponseEntity<>(resultCsvData.getBytes("MS932"), httpHeaders, HttpStatus.OK);
	}

	// 新規追加,更新,削除ボタン押下時の処理
	@Transactional
	@RequestMapping("search/edit")
	public ModelAndView edit(AddressEditForm form, ModelAndView mav) {
		AddressDto addressDto = new AddressDto();
		editService.setDto(form, addressDto);

		String selectUserAction = addressDto.getEdit();

		switch (selectUserAction) {
		case "登録":
			addressService.exeInsert(addressDto);
			break;
		case "更新":
			addressService.exeUpDate(addressDto);
			break;
		default:
			addressService.exeDelete(addressDto);
			break;
		}

		mav.setViewName("search");
		return mav;
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public String NullPointerExceptionHandler() {
        System.out.println("NullPointerExceptionが発生しました。");
        return "NullPointerExceptionが発生しました。";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public String IllegalArgumentExceptionHandler() {
        System.out.println("IllegalArgumentExceptionが発生しました。");
        return "IllegalArgumentExceptionが発生しました。";
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public String handleError() {
    	System.out.println("その他例外が発生しました。");
        return "その他例外が発生しました。";
    }
}
