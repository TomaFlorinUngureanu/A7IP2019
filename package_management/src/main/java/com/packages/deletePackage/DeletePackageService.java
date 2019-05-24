package com.packages.deletePackage;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packages.exceptions.UnknownMatchException;
import com.packages.model.JwtUser;
import com.packages.model.PackagesSenderHistory;
import com.packages.repositories.CommandsHistoryRepository;

@Service
public class DeletePackageService {

	@Autowired
	CommandsHistoryRepository cmdRepo;

	public String deletePackage(int id) throws JSONException {

		if (!cmdRepo.existsById(id))
			throw new UnknownMatchException("The package with the given id doen't exists");

		PackagesSenderHistory cmd = new PackagesSenderHistory();
		cmd = cmdRepo.findById(id).get();

		if (!cmd.getEmailSender().equals(JwtUser.getUserName()))
			throw new UnknownMatchException("You can't delete a package that isn't placed by you");

		if (!cmd.getStatus().equals("Ready"))
			throw new UnknownMatchException("You cant't delete a package that doesn't have Ready status");

		cmdRepo.deleteById(id);

		JSONObject res = new JSONObject();
		res.put("message", "Success");
		return res.toString();
	}

}
