-- Reporte: ranking (top 10) de clientes con mayor cantidad de reclamos
SELECT TOP 10 cli.nombre as 'Cliente', COUNT (*) as 'Cant. Reclamos'
FROM reclamos rec
INNER JOIN clientes cli ON rec.idcliente = cli.idcliente
GROUP BY cli.nombre
ORDER BY 'Cant. Reclamos' DESC

-- Reporte: cantidad de reclamos por mes
SELECT Mes = CASE
	WHEN MONTH(rec.fecha) = 11 then 'Noviembre'
	WHEN MONTH(rec.fecha) = 12 then 'Diciembre'
	END,
	COUNT (*) as 'Cantidad de Reclamos'
FROM reclamos rec
GROUP BY MONTH(rec.fecha)

-- Reporte: ranking de tratamiento de reclamos
SELECT TOP 10 us.username as 'Usuario', COUNT (*) as 'Cantidad'
FROM usuarios us 
INNER JOIN auditoriasreclamos aud ON us.idusuario = aud.idusuario
GROUP BY us.username

-- Reporte: tiempo promedio de respuesta de los reclamos por responsable
SELECT usr.username, SUM(DATEDIFF(hh, rec.fecha, aud.fecha))/COUNT (aud.idusuario) as 'promedio de tiempo'
FROM reclamos rec 
INNER JOIN auditoriasreclamos aud ON rec.nroreclamo = aud.nroreclamo
INNER JOIN usuarios usr ON aud.idusuario = usr.idusuario
WHERE aud.datonuevo = 'Solucionado'
GROUP BY usr.username


