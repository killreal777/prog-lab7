UPDATE organizations SET  organization_name = ?, organization_full_name = ?,
                          organization_coordinate_x = ?, organization_coordinate_y = ?,
                          annual_turnover = ?, employees_count = ?,
                          organization_type = ?,
                          zip_code = ?,
                          location_coordinate_x = ?, location_coordinate_y = ?, location_coordinate_z = ?,
                          location_name = ?,
                          owner_login = ?
WHERE organization_id = ?;