# -*- coding: utf-8 -*-
# Generated by Django 1.11.7 on 2017-11-15 20:59
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('catalogs', '0003_procedure'),
    ]

    operations = [
        migrations.AlterModelOptions(
            name='procedure',
            options={'ordering': ['order']},
        ),
        migrations.AddField(
            model_name='procedure',
            name='content',
            field=models.TextField(blank=True, default=''),
        ),
    ]